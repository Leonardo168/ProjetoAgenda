package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://localhost:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "th8b7thZD[JC288zn+oi";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Falha em conectar()");
			return null;
		}
	}

	/**
	 * Creates the.
	 *
	 * @param contato the contato
	 */
	public void create(JavaBeans contato) {
		String SQLcreate = "insert into contatos (nome,fone,email) values(?,?,?)";

		try {
			Connection con = conectar();
			PreparedStatement myStmt = con.prepareStatement(SQLcreate);
			myStmt.setString(1, contato.getNome());
			myStmt.setString(2, contato.getFone());
			myStmt.setString(3, contato.getEmail());
			myStmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("erro em create()");
		}
	}

	/**
	 * Read.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> read() {
		String SQLread = "select * from contatos";
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		try {
			Connection con = conectar();
			PreparedStatement myStmt = con.prepareStatement(SQLread);
			ResultSet rs = myStmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				contatos.add(new JavaBeans(id, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("erro em listarContatos()");
			return null;
		}
	}

	/**
	 * Select.
	 *
	 * @param contato the contato
	 */
	public void select(JavaBeans contato) {
		String SQLread = "select * from contatos where id = ?;";
		try {
			Connection con = conectar();
			PreparedStatement myStmt = con.prepareStatement(SQLread);
			myStmt.setInt(1, contato.getId());
			ResultSet rs = myStmt.executeQuery();
			while (rs.next()) {
				contato.setId(rs.getInt(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Update.
	 *
	 * @param contato the contato
	 */
	public void update(JavaBeans contato) {
		String SQLupdate = "update contatos set nome=?, fone=?, email=? where id = ?";
		try {
			Connection con = conectar();
			PreparedStatement myStmt = con.prepareStatement(SQLupdate);
			myStmt.setString(1, contato.getNome());
			myStmt.setString(2, contato.getFone());
			myStmt.setString(3, contato.getEmail());
			myStmt.setInt(4, contato.getId());
			myStmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Delete.
	 *
	 * @param contato the contato
	 */
	public void delete(JavaBeans contato) {
		String SQLdelete = "delete from contatos where id=?";
		try {
			Connection con = conectar();
			PreparedStatement myStmt = con.prepareStatement(SQLdelete);
			myStmt.setInt(1, contato.getId());
			myStmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
