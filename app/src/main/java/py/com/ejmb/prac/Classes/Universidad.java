package py.com.ejmb.prac.Classes;

/**
 * Created by Eduardo on 15/9/2016.
 */
public class Universidad {
    private int universidadCod; //Codigo
    private String universidadDes;  //Descripcion

    public Universidad() {
    }
    public Universidad(int universidadCod, String universidadDes) {
        this.universidadCod = universidadCod;
        this.universidadDes = universidadDes;
    }

    public int getUniversidadCod() {
        return universidadCod;
    }

    public void setUniversidadCod(int universidadCod) {
        this.universidadCod = universidadCod;
    }

    public String getUniversidadDes() {
        return universidadDes;
    }

    public void setUniversidadDes(String universidadDes) {
        this.universidadDes = universidadDes;
    }
}
