package com.thanglv.hrfreelancer.view;


import com.thanglv.hrfreelancer.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.keycloak.AuthorizationContext;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.idm.authorization.Permission;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named("commandButtonView")
@RequestScoped
public class CommandButtonView implements Serializable {

    private final Logger logger = LogManager.getLogger();

    @Inject
    private HttpServletRequest request;

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    public void action() throws InterruptedException {
        KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        AuthorizationContext authzContext = keycloakSecurityContext.getAuthorizationContext();
        for (Permission p : authzContext.getPermissions()) {
            logger.info( p.getResourceName());
        }
        Thread.sleep(5000);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Xin trân trọng thông báo", "Bây giờ là: " + String.valueOf(entityManager.createNativeQuery("select now()").getResultList().get(0)) );
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
