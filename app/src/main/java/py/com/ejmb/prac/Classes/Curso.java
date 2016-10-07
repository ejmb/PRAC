package py.com.ejmb.prac.Classes;

/**
 * Created by Eduardo on 15/9/2016.
 */
public class Curso {
    private int cursoCod;   //Codigo
    private String cursoDes;    //Descripcion
    private int cursoCan;   //Limite de Horas

    public Curso() {
    }
    public Curso(int cursoCod, String cursoDes, int cursoCan) {
        this.cursoCod = cursoCod;
        this.cursoDes = cursoDes;
        this.cursoCan = cursoCan;
    }

    public int getCursoCod() {
        return cursoCod;
    }

    public void setCursoCod(int cursoCod) {
        this.cursoCod = cursoCod;
    }

    public String getCursoDes() {
        return cursoDes;
    }

    public void setCursoDes(String cursoDes) {
        this.cursoDes = cursoDes;
    }

    public int getCursoCan() {
        return cursoCan;
    }

    public void setCursoCan(int cursoCan) {
        this.cursoCan = cursoCan;
    }
}
