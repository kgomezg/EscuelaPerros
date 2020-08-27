/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaperros;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 * @date 2020.08.24
 * @desc
 */
public class Escuela {

    private static final Object[] opciones = {"Seleccione", "Agregar Nuevo Perro", "Buscar Perro por Código", "Eliminar un Perro", "Perros por Propietario", "Perros por Raza", "Perros por Localidad", "Salir"};
    private String nombreEscuela = "Firulais";
    private List<Perro> perros = new ArrayList<>();
    private List<String> propietarios = new ArrayList<>();
    private List<String> razas = new ArrayList<>();
    private List<String> localidades = new ArrayList<>();

    public void Escuela() {
        propietarios.add("Seleccione");
        razas.add("Seleccione");
        localidades.add("Seleccione");

        _seleccion(true);
    }

    public void _seleccion(boolean b) {
        if (b) {
            JOptionPane.showMessageDialog(null, "Bienvenidos a " + nombreEscuela + "\nSeleccione una opción correcta, por favor.");
        } else {
            JOptionPane.showMessageDialog(null, "Volvera al inicio.", nombreEscuela, JOptionPane.INFORMATION_MESSAGE, null);
        }

        Object seleccion = JOptionPane.showInputDialog(
                null,
                "Menú de Opciones",
                nombreEscuela,
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono defecto
                opciones,
                opciones[0]);

        if (seleccion == null) {
            _seleccion(false);
        } else if (seleccion.toString().length() > 0) {

            if (seleccion.equals(opciones[0])) {

                JOptionPane.showMessageDialog(null, "Seleccione una opción correcta, por favor.");
                _seleccion(b);

            } else if (seleccion.equals(opciones[1])) { // Agregar Nuevo Perro

                if (_AgregarPerro(
                        (String) JOptionPane.showInputDialog(null, "Digite el código del perro.", opciones[1].toString(), JOptionPane.QUESTION_MESSAGE, null, null, ""),
                        (String) JOptionPane.showInputDialog(null, "Digite la raza del perro.", opciones[1].toString(), JOptionPane.QUESTION_MESSAGE, null, null, ""),
                        (String) JOptionPane.showInputDialog(null, "Digite el nombre del perro.", opciones[1].toString(), JOptionPane.QUESTION_MESSAGE, null, null, ""),
                        (String) JOptionPane.showInputDialog(null, "Digite la localidad del perro.", opciones[1].toString(), JOptionPane.QUESTION_MESSAGE, null, null, ""),
                        (String) JOptionPane.showInputDialog(null, "Digite la cedula del propietario.", opciones[1].toString(), JOptionPane.QUESTION_MESSAGE, null, null, ""),
                        (String) JOptionPane.showInputDialog(null, "Digite el nombre del propietario.", opciones[1].toString(), JOptionPane.QUESTION_MESSAGE, null, null, "")
                )) {
                    JOptionPane.showMessageDialog(null, "El perro fue registrado en el sistema con exito.");
                    _seleccion(true);
                } else {
                    JOptionPane.showMessageDialog(null, "El código que trata de registrar ya se encuentra en el sistema.\nPor favor valide.");
                    _seleccion(false);
                }

            } else if (seleccion.equals(opciones[2])) { // Buscar Perro por Codigo

                JOptionPane.showMessageDialog(null,
                        _ObtenerPerro(
                                (String) JOptionPane.showInputDialog(null, "Digite el código del perro.", opciones[2].toString(), JOptionPane.QUESTION_MESSAGE, null, null, "")
                        ), nombreEscuela, JOptionPane.INFORMATION_MESSAGE, null);

                _seleccion(false);

            } else if (seleccion.equals(opciones[3])) { // Eliminar un Perro

                int posPerro = _BusquedaPerroEliminar(
                        (String) JOptionPane.showInputDialog(null, "Digite el código del perro.", opciones[3].toString(), JOptionPane.QUESTION_MESSAGE, null, null, "")
                );

                if (posPerro >= 0) {
                    if (JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro del perro?", opciones[3].toString(), JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == 0) { // SI
                        _EliminarPerro(posPerro);
                        JOptionPane.showMessageDialog(null, "Se ha eliminado el registro exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha eliminado el registro.");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro al perro.\nDigite una código correcto, por favor.");
                }

                _seleccion(false);

            } else if (seleccion.equals(opciones[4])) { // Perros por Propietario

                JOptionPane.showMessageDialog(null,
                        _PerrosPropietario(
                                (String) JOptionPane.showInputDialog(null, "Digite la cédula del propietario.", opciones[4].toString(), JOptionPane.QUESTION_MESSAGE, null, null, "")
                        ), nombreEscuela, JOptionPane.INFORMATION_MESSAGE, null);

                _seleccion(false);

            } else if (seleccion.equals(opciones[5])) { // Perros por Raza

                JOptionPane.showMessageDialog(null,
                        _PerrosRaza(
                                (String) JOptionPane.showInputDialog(null, "Digite la raza.", opciones[4].toString(), JOptionPane.QUESTION_MESSAGE, null, null, "")
                        ), nombreEscuela, JOptionPane.INFORMATION_MESSAGE, null);

                _seleccion(false);

            } else if (seleccion.equals(opciones[6])) { // Perros por Localidad

                JOptionPane.showMessageDialog(null,
                        _PerrosLocalidad(
                                (String) JOptionPane.showInputDialog(null, "Digite localidad.", opciones[5].toString(), JOptionPane.QUESTION_MESSAGE, null, null, "")
                        ), nombreEscuela, JOptionPane.INFORMATION_MESSAGE, null);

                _seleccion(false);

            } else if (seleccion.equals(opciones[7])) { // Salir
                _salir();
            }
        }
    }

    public boolean _AgregarPerro(String _cod, String _raz, String _nom, String _loc, String _cedPro, String _nomProp) {
        boolean rta = false;

        Perro _p = new Perro(_cod, _raz, _nom, _loc, _cedPro, _nomProp);

        if (perros.isEmpty() || !_ExistePerro(_p.getCodigo())) {
            rta = perros.add(_p);

            if (!_ExisteRaza(_p.getRaza())) {
                razas.add(_p.getRaza().toUpperCase());
            }

            if (!_ExisteLocalidad(_p.getLocalidad())) {
                localidades.add(_p.getLocalidad().toUpperCase());
            }

            if (!_ExistePropietario(_p.getCedulaPropietario())) {
                propietarios.add(_p.getCedulaPropietario().toUpperCase());
            }
        }

        return rta;
    }

    public boolean _ExistePerro(String _cod) {
        return perros.stream().filter(o -> o.getCodigo().equals(_cod)).findFirst().isPresent();
    }

    public boolean _ExisteRaza(String _raza) {
        return razas.stream().filter(o -> o.toUpperCase().equals(_raza.toUpperCase())).findFirst().isPresent();
    }

    public boolean _ExisteLocalidad(String _localidad) {
        return localidades.stream().filter(o -> o.toUpperCase().equals(_localidad.toUpperCase())).findFirst().isPresent();
    }

    public boolean _ExistePropietario(String _prop) {
        return propietarios.stream().filter(o -> o.toUpperCase().equals(_prop.toUpperCase())).findFirst().isPresent();
    }

    public void _salir() {

        switch (JOptionPane.showConfirmDialog(null, "¿Señor(a) Usuario(a), ¿Desea salir del administrador?", nombreEscuela,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
            case 0 -> {
                JOptionPane.showMessageDialog(null, "Gracias por usar el administrador.", nombreEscuela, JOptionPane.INFORMATION_MESSAGE, null);
            }
            case 1 -> {
                _seleccion(false);
            }
        }
    }

    public String _ObtenerPerro(String _cod) {
        int pos = -1;

        for (Perro p : perros) {
            if (p.getCodigo().equals(_cod)) {
                pos = perros.indexOf(p);
            }
        }

        return (pos >= 0 ? perros.get(pos).toString() : "No se encontro el perro.\nPor favor verifique el código.");
    }

    public int _BusquedaPerroEliminar(String _cod) {
        Integer pos = -1;

        for (Perro p : perros) {
            if (p.getCodigo().equals(_cod)) {
                pos = perros.indexOf(p);
            }
        }

        return pos;
    }

    public Boolean _EliminarPerro(Integer _pos) {
        boolean rta = false;

        if (perros.get(_pos) != null && _pos >= 0) {
            rta = perros.remove(perros.get(_pos));
        }

        return rta;
    }

    public String _PerrosPropietario(String _cedPro) {
        List<Perro> _perrosPropietario = new ArrayList<>();
        String nombresPerros = "";

        for (Perro p : perros) {
            if (p.getCedulaPropietario().equals(_cedPro)) {
                _perrosPropietario.add(p);
                nombresPerros = p.getNombre() + (_perrosPropietario.indexOf(p) == 0 ? " " : ", ");
            }
        }

        return "El propietario de cedula " + _cedPro + " es dueño de:\n" + nombresPerros;
    }

    public String _PerrosRaza(String _raz) {
        List<Perro> _perrosRaza = new ArrayList<>();
        String nombres = "";

        for (Perro p : perros) {
            if (p.getRaza().equals(_raz)) {
                _perrosRaza.add(p);
                nombres += p.getNombre() + (_perrosRaza.indexOf(p) == 0 ? " " : ", ");
            }
        }

        return "Existen " + _perrosRaza.size() + " de raza " + _raz + " y sus nombres son:\n" + nombres;
    }

    public String _PerrosLocalidad(String _loc) {
        List<Perro> _perrosLocalidad = new ArrayList<>();
        String nombres = "";

        for (Perro p : perros) {
            if (p.getLocalidad().equals(_loc)) {
                _perrosLocalidad.add(p);
                nombres += p.getNombre() + (_perrosLocalidad.indexOf(p) == 0 ? " " : ", ");
            }
        }

        return "Existen " + _perrosLocalidad.size() + " en la localidad " + _loc + " y sus nombres son:\n" + nombres;
    }

    public String getNombreEscuela() {
        return nombreEscuela;
    }

    public void setNombreEscuela(String nombreEscuela) {
        this.nombreEscuela = nombreEscuela;
    }

}
