package br.ufes.view.usuario;

import br.ufes.models.Usuario;
import br.ufes.observable.Observado;
import br.ufes.singleton.JInternalCentralizador;
import br.ufes.view.usuario.state.EditarUsuarioState;
import br.ufes.view.usuario.state.InserirUsuarioState;
import br.ufes.view.usuario.state.ManterUsuarioState;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author gabriel
 */
public class ManterUsuarioPresenter extends Observado{
    
    private UsuarioView view;
    private ManterUsuarioState estado;

    public ManterUsuarioPresenter(Usuario usuario, JDesktopPane desktop) {
        this.view = new UsuarioView();
        this.setTela(usuario);
        this.estado = new EditarUsuarioState(this, usuario.getId());
        
        
        this.view.getBtnConfirmar().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario u = getTela();
                if(u!=null){
                    estado.executar(u);
                    notificarObservadores();
                }
                    
            }
        });
        
        JInternalCentralizador.getInstancia().centralizarView(view, desktop);
    }

    public ManterUsuarioPresenter(JDesktopPane desktop) {
        this.view = new UsuarioView();
        this.estado = new InserirUsuarioState(this);
        
        this.view.getBtnConfirmar().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario u = getTela();
                if(u!=null){
                    estado.executar(u);
                    notificarObservadores();
                }
            }
        });
        
        JInternalCentralizador.getInstancia().centralizarView(view, desktop);
    }
    
    private void setTela(Usuario usuario){
        this.view.getTxtLogin().setText(usuario.getLogin());
        this.view.getTxtLogin().setEnabled(false);
        this.view.getAdministrador().setSelected(usuario.isAdimin());
    }
    
    private Usuario getTela(){
        String senha = String.valueOf(this.view.getTxtSenha().getPassword());
        String confere = String.valueOf(this.view.getTxtConfirmaSenha().getPassword());
        if(!senha.equals(confere)){
            JOptionPane.showMessageDialog(view, "Campo de senha não conferem, tente novamente!", "Erro na senha", 1);
        }else if(senha.length() < 8){
            JOptionPane.showMessageDialog(view, "Senha muito curta, mínimo de 8 caracteres!", "Erro na senha", 1);
        }else{
            return new Usuario(this.view.getTxtLogin().getText(), String.valueOf(this.view.getTxtSenha().getPassword()), this.view.getAdministrador().isSelected());
        }
        
        return null;
    }

    public UsuarioView getView() {
        return view;
    }
    
    
}
