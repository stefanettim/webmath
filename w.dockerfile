FROM maven as maven_jar_builder
COPY m/pom.xml pom.xml
COPY m/src src
RUN ["mvn","clean","install","-T","2C","-DskipTests=true"]

FROM maven as maven_war_builder
COPY w/pom.xml pom.xml
COPY w/src src
COPY --from=maven_jar_builder /root/.m2/repository/xyz/mstef/m/0.0.2/m-0.0.2.jar .
COPY --from=maven_jar_builder /root/.m2/repository/xyz/mstef/m/0.0.2/m-0.0.2.pom .
RUN ["mvn","install:install-file","-Dfile=m-0.0.2.jar","-DpomFile=m-0.0.2.pom"]
RUN ["mvn","clean","install","-T","2C","-DskipTests=true"]

FROM tomcat
COPY --from=maven_war_builder /root/.m2/repository/xyz/mstef/w/0.4/w-0.4.war /usr/local/tomcat/webapps

