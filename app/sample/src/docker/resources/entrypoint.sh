#!/bin/bash
set -e

# Environment:
# JAVA_MEM: setup java heap, JMS and JMX
# JAVA_JMX: setup java JMX parameters
# JAVA_PERFORMANCE: setup performance tunning parameters, ex: gc
# JAVA_OPTS: default flag for all other parameters

exec java ${JAVA_OPTS} ${JAVA_MEM} ${JAVA_JMX} ${JAVA_PERFORMANCE} \
	-jar ${SERVICE_HOME}/sample.jar $*