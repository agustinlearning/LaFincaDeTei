package logica;

import java.sql.*;

public class GestorBaseDatos {

    private static final String URL = "jdbc:sqlite:datos_finca.db";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Error al conectar con SQLite: " + e.getMessage());
        }
        return conexion;
    }

    public static void inicializarEstructura() {
        String sqlBovino = "CREATE TABLE IF NOT EXISTS Bovino ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "tipo TEXT NOT NULL, "
                + "nombre TEXT, "
                + "urlImagen TEXT, "
                + "fechaNac TEXT, "
                + "raza TEXT, "
                + "procedencia TEXT, "
                + "vivo INTEGER"
                + ");";

        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sqlBovino);
            System.out.println("Base de datos inicializada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear las tablas: " + e.getMessage());
        }
    }


    public static void insertarVaca(Vaca vaca) {
        String sql = "INSERT INTO Bovino(tipo, nombre, urlImagen, fechaNac, raza, procedencia, vivo) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, "VACA");
            pstmt.setString(2, vaca.getNombre());
            pstmt.setString(3, vaca.urlImagen);
            pstmt.setString(4, vaca.getFechaNac().toString());
            pstmt.setString(5, vaca.getRaza());
            pstmt.setString(6, vaca.getProcedencia());
            pstmt.setInt(7, vaca.getVivo() ? 1 : 0); // SQLite no tiene boolean

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long idGenerado = generatedKeys.getLong(1);
                    // Actualizamos el objeto con su nuevo ID obtenido de la base de datos
                    vaca.setId("B-" + idGenerado);
                    System.out.println("Vaca guardada exitosamente con el ID: " + vaca.getId());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar la vaca: " + e.getMessage());
        }
    }
}
