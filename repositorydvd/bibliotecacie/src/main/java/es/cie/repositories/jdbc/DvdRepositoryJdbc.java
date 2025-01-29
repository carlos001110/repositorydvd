package es.cie.repositories.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.cie.negocio.Dvd;
import es.cie.repositories.DvdRepository;

public class DvdRepositoryJdbc implements DvdRepository {
	static final String DB_URL = "jdbc:mysql://localhost:3306/biblioteca";
	static final String USER = "root";
	static final String PASS = "";

	@Override
	public List<Dvd> buscartodos() {
		Connection conexion = null;
		Statement sentencia = null;
		ResultSet rs = null;
		// registra el driver para conectar
		List<Dvd> lista = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(DB_URL, USER, PASS);
			// para preparar la sentencia sql al lanzar
			sentencia = conexion.createStatement();
			// una vez ejecuto la sentencia
			// el resultado se asigno a un resultado
			rs = sentencia.executeQuery("select * from dvd");
			while (rs.next()) {
				Dvd d = new Dvd(rs.getInt("iddvd"),rs.getString("titulo"), rs.getInt("anio"), rs.getBoolean("disponible"));
				lista.add(d);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		  } finally {
	            try {
	                if (rs != null) {
						rs.close();
					}
	                if (sentencia != null) {
						sentencia.close();
					}
	                if (conexion != null) {
						conexion.close();
					}
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
		}
		return lista;
	}

	@Override
	public void insertar(Dvd dvd) {
	    Connection conexion = null;
	    Statement sentencia = null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conexion = DriverManager.getConnection(DB_URL, USER, PASS);
	        sentencia = conexion.createStatement();

	        // Sin el replace, asumiendo que no hay comillas simples en el título
	        String insertarSql = "INSERT INTO dvd (titulo, anio, disponible) VALUES ('" + dvd.getTitulo() + "', " + dvd.getAnio() + ", " + (dvd.getDisponible() ? "true" : "false") + ")";

	        // Ejecutar la consulta
	        sentencia.executeUpdate(insertarSql);

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (sentencia != null) {
					sentencia.close();
				}
	            if (conexion != null) {
					conexion.close();
				}
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


	@Override
	public void borrar(Dvd dvd) {
		Connection conexion=null;
		Statement sentencia=null;
		try {

		Class.forName("com.mysql.jdbc.Driver");
		conexion=DriverManager.getConnection(DB_URL,USER,PASS);
		sentencia=conexion.createStatement();

		String borrarSql=("delete from  dvd where  iddvd='" + dvd.getIddvd()+"'");
		sentencia.executeUpdate(borrarSql);

		System.out.println(borrarSql);

	}catch(ClassNotFoundException  | SQLException e){
		e.printStackTrace();
	}

	}

	@Override
	public List<Dvd> buscarTodosOrdenados(String orden) {
		Connection conexion=null;
		Statement sentencia=null;
		ResultSet rs=null;
		//registra el driver para conectar
		List<Dvd> lista= new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion=DriverManager.getConnection(DB_URL,USER,PASS);
			//para preparar la sentencia sql al lanzar
			sentencia=conexion.createStatement();
			//una vez ejecuto la sentencia
			//el resultado se asigno a un resultado
			 rs=sentencia.executeQuery("select * from dvd order by "+ orden);
			 while(rs.next()) {
				 Dvd s=new Dvd(rs.getInt("iddvd"), rs.getString("titulo"),rs.getInt("anio"),rs.getBoolean("disponible"));
				 lista.add(s);
			 }
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();


		} finally {
            try {
                if (sentencia != null) {
					sentencia.close();
				}
                if (conexion != null) {
					conexion.close();
				}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

		return lista;

}
}