package com.thanglv.hrfreelancer.view;


import com.thanglv.hrfreelancer.App;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Named("commandButtonView")
@RequestScoped
public class CommandButtonView implements Serializable {

    @Inject
    private App app;

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    public void action() throws InterruptedException {
        Thread.sleep(5000);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Xin trân trọng thông báo", "Bây giờ là: " + String.valueOf(entityManager.createNativeQuery("select now()").getResultList().get(0)) );
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
