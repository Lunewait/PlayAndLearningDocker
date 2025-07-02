FROM tomcat:9.0

# Elimina las apps por defecto
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia tu archivo WAR al Tomcat
COPY PlayAndLearningProject.war /usr/local/tomcat/webapps/ROOT.war
