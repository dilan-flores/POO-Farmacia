import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Farmacia {
    Statement s;
    ResultSet rs;
    PreparedStatement ps;
    public JPanel Panel;
    private JButton actualizar;
    private JButton crear;
    private JButton buscar;
    private JButton borrar;
    private JFormattedTextField textID;
    private JFormattedTextField textNOMBRE;
    private JFormattedTextField textDESCRIPCION;
    private JComboBox cbCANTIDAD;
    private JComboBox cbVENCIDA;

    public Farmacia() {
        /*Almacena temporalmente los datos pertenecientes a JComboBox*/
        JFormattedTextField cantidad = new JFormattedTextField();
        JFormattedTextField vencido = new JFormattedTextField();

        try {/*Almacena en los JComboBox todos los datos de la BD*/
            Connection conexion;
            conexion = getConection();

            s = conexion.createStatement();
            rs = s.executeQuery("SELECT * FROM productos ");

            cbCANTIDAD.removeAllItems();
            cbVENCIDA.removeAllItems();
            cbCANTIDAD.addItem(" ");
            cbVENCIDA.addItem(" ");
            while (rs.next()) {
                cbCANTIDAD.addItem(rs.getString(4));
                cbVENCIDA.addItem(rs.getString(5));
            }
            conexion.close();
            rs.close();
            s.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                /*
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/farmacia", "root", "3001a"
                    );
                    Connection conexion;
                    conexion = getConection();
                    Statement s = conexion.createStatement();

                    ResultSet rs = s.executeQuery("SELECT * FROM productos ");

                    cbCANTIDAD.removeAllItems();
                    cbVENCIDA.removeAllItems();
                    cbCANTIDAD.addItem(" ");
                    cbVENCIDA.addItem(" ");
                    while (rs.next()) {
                            cbCANTIDAD.addItem(rs.getString(4));
                            cbVENCIDA.addItem(rs.getString(5));
                    }
                 */
                    Connection conexion;
                    conexion = getConection();

                    String id = textID.getText();
                    s = conexion.createStatement();
                    rs = s.executeQuery("SELECT * FROM productos WHERE id =" + id);

                    while (rs.next()) {
                        if (textID.getText().equals(rs.getString(1))) {
                            textNOMBRE.setText(rs.getString(2));
                            textDESCRIPCION.setText(rs.getString(3));
                            cantidad.setText(rs.getString(4)); /*Captura los datos de cantidad*/
                            vencido.setText(rs.getString(5)); /*Captura los datos de cantidad vencida*/
                            cbCANTIDAD.setSelectedItem(cantidad.getText());/*Presenta el valor correspondiente al ID*/
                            cbVENCIDA.setSelectedItem(vencido.getText()); /*Presenta el valor correspondiente al ID*/
                        } else {
                            JOptionPane.showMessageDialog(null, "DATOS NO ENCONTRADOS");
                        }
                    }
                    conexion.close();
                    rs.close();
                    s.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    /*
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/farmacia", "root", "3001a"
                    );
                     */
                    Connection conexion;
                    conexion = getConection();

                    ps = conexion.prepareStatement("Insert into productos values (?,?,?,?,?)");
                    ps.setString(1, textID.getText());
                    ps.setString(2, textNOMBRE.getText());
                    ps.setString(3, textDESCRIPCION.getText());

                    cantidad.setText((String)cbCANTIDAD.getSelectedItem()); /*Captura el valor seleccionado*/
                    vencido.setText((String)cbVENCIDA.getSelectedItem()); /*Captura el valor seleccionado*/

                    ps.setString(4, cantidad.getText());/*Registra el valor capturado*/
                    ps.setString(5, vencido.getText()); /*Registra el valor capturado*/

                    /*
                    cbCANTIDAD.setSelectedItem(cantidad.getText());
                    cbVENCIDA.setSelectedItem(vencido.getText());
                     */

                    int res = ps.executeUpdate();
                    if(res >0){
                        JOptionPane.showMessageDialog(null,"CREACIÓN CON ÉXITO");
                    }else{
                        JOptionPane.showMessageDialog(null,"NO GUARDADO");
                    }

                    conexion.close();
                    rs.close();
                    s.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PreparedStatement ps;
                try{
                    /*
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/farmacia", "root", "3001a"
                    );
                     */
                    Connection conexion;
                    conexion = getConection();

                    ps = conexion.prepareStatement("UPDATE productos SET id = ?,nombre = ?, descripcion= ?, cantidad =?, cantidad_vencidad =?  WHERE id = " + textID.getText());
                    ps.setString(1, textID.getText());
                    ps.setString(2, textNOMBRE.getText());
                    ps.setString(3, textDESCRIPCION.getText());
                    cantidad.setText((String)cbCANTIDAD.getSelectedItem()); /*Captura el valor seleccionado*/
                    vencido.setText((String)cbVENCIDA.getSelectedItem()); /*Captura el valor seleccionado*/
                    ps.setString(4, cantidad.getText()); /*Registra el valor capturado*/
                    ps.setString(5, vencido.getText()); /*Registra el valor capturado*/

                    int res = ps.executeUpdate();
                    if(res >0){
                        JOptionPane.showMessageDialog(null,"PRODUCTO " + textID.getText() + " ACTUALIZADO");
                    }else{
                        JOptionPane.showMessageDialog(null,"NO GUARDADO");
                    }
                    conexion.close();//importante!!!!
                    rs.close();
                    s.close();
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PreparedStatement ps;
                try{
                    /*
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/farmacia", "root", "3001a"
                    );
                     */
                    Connection conexion;
                    conexion = getConection();
                    /*set SQL_SAFE_UPDATES=0; // Código para poder eliminación un registro de la tabla*/
                    ps = conexion.prepareStatement("DELETE from productos WHERE id = " + textID.getText() );

                    int res = ps.executeUpdate();
                    if(res >0){
                        JOptionPane.showMessageDialog(null,"PRODUCTO " + textID.getText() + " ELIMINADO");
                    }else{
                        JOptionPane.showMessageDialog(null,"NO GUARDADO");
                    }
                    conexion.close();//importante!!!!
                    rs.close();
                    s.close();
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public static Connection getConection()
    {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/farmacia", "root", "3001a"
            );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conexion;
    }
}

