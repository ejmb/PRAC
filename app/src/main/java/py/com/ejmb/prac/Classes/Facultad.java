package py.com.ejmb.prac.Classes;

/**
 * Created by Eduardo on 15/9/2016.
 */
public class Facultad {
    private int facultadCod;    //Codigo
    private String facultadDes; //Descripcion

    public Facultad() {
    }
    public Facultad(int facultadCod, String facultadDes) {
        this.facultadCod = facultadCod;
        this.facultadDes = facultadDes;
    }

    public int getFacultadCod() {
        return facultadCod;
    }

    public void setFacultadCod(int facultadCod) {
        this.facultadCod = facultadCod;
    }

    public String getFacultadDes() {
        return facultadDes;
    }

    public void setFacultadDes(String facultadDes) {
        this.facultadDes = facultadDes;
    }
}
