package py.com.ejmb.prac.Classes;

/**
 * Created by Eduardo on 15/9/2016.
 */
public class Usuario {
    private String usuarioDoc;  //Documento PK
    private String usuarioNom;  //Nombre
    private String usuarioApe;  //Apellido

    public Usuario() {
    }
    public Usuario(String usuarioDoc, String usuarioNom, String usuarioApe) {
        this.usuarioDoc = usuarioDoc;
        this.usuarioNom = usuarioNom;
        this.usuarioApe = usuarioApe;
    }

    public String getusuarioDoc() {
        return usuarioDoc;
    }

    public void setusuarioDoc(String usuarioDoc) {
        this.usuarioDoc = usuarioDoc;
    }

    public String getUsuarioNom() {
        return usuarioNom;
    }

    public void setUsuarioNom(String usuarioNom) {
        this.usuarioNom = usuarioNom;
    }

    public String getUsuarioApe() {
        return usuarioApe;
    }

    public void setUsuarioApe(String usuarioApe) {
        this.usuarioApe = usuarioApe;
    }
}
