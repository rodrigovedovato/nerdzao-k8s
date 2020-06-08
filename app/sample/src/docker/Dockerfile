FROM adoptopenjdk/openjdk11
LABEL maintainer "Rodrigo Vedovato <engenharia.rdrgvedovato@elo7.com>"

WORKDIR /opt/sample
ENTRYPOINT [ "/opt/sample/entrypoint.sh" ]
ENV SERVICE_HOME=/opt/sample

# Install
ARG VERSION
ENV APPLICATION_VERSION=${VERSION}
LABEL version=${VERSION}
ADD resources /opt/sample
ADD sample-$VERSION-shaded.jar /opt/sample/sample.jar