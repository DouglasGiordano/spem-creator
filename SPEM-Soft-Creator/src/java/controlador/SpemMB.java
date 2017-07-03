package controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author douglas
 */
@Named(value = "spemMB")
@ViewScoped
public class SpemMB implements Serializable{

    private byte[] arquivo;
    
    
    @PostConstruct
    public void init() {
    }

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public SpemMB() {
    }

    /**
     * @return the arquivo
     */
    public byte[] getArquivo() {
        return arquivo;
    }

    /**
     * @param arquivo the arquivo to set
     */
    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public void addSpem(FileUploadEvent event) {
        this.arquivo = event.getFile().getContents();
        
    }

    public void criarSoftwareSuporte(){
        System.out.println(arquivo.toString());
    }
    
}
