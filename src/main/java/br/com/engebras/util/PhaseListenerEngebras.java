package br.com.engebras.util;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.hibernate.Session;

/**
 * @author Adalberto
 * dt. criação: 23/03/2016
 */
public class PhaseListenerEngebras implements PhaseListener{
    @Override
    public void beforePhase(PhaseEvent fase) {
        System.out.println("Antes da fase: " + getPhaseId());
        if (fase.getPhaseId().equals(PhaseId.RESTORE_VIEW)) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            FacesContextUtil.setRequestSession(session);
        }
    }

    @Override
    public void afterPhase(PhaseEvent fase) {
        System.out.println("Depois da fase:" + getPhaseId());
        if (fase.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
            Session session = FacesContextUtil.getRequestSession();
            try {
                session.getTransaction().commit();
            } catch (Exception ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally {
                session.close();
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
    
}
