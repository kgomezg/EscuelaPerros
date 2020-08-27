/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaperros;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 * @date 2020.08.24
 * @desc
 */
public class Perro {

    private String codigo;
    private String raza;
    private String nombre;
    private String localidad;
    private String cedulaPropietario;
    private String nombrePropietario;

    public Perro() {

    }

    public Perro(String _cod, String _raz, String _nom, String _loc, String _cedPro, String _nomProp) {
        this.codigo = _cod;
        this.raza = _raz;
        this.nombre = _nom;
        this.localidad = _loc;
        this.cedulaPropietario = _cedPro;
        this.nombrePropietario = _nomProp;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCedulaPropietario() {
        return cedulaPropietario;
    }

    public void setCedulaPropietario(String cedulaPropietario) {
        this.cedulaPropietario = cedulaPropietario;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    @Override
    public String toString() {

        return "Nombre: " + getNombre() + "\n"
                + "Codigo: " + getCodigo() + "\n"
                + "Raza: " + getRaza() + "\n"
                + "Propietario: " + getCedulaPropietario() + " - " + getNombrePropietario() + "\n"
                + "Localidad: " + getLocalidad() + "\n"
                + "";
    }
}
