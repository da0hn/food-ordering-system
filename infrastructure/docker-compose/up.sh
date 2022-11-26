#!/usr/bin/env bash

docker-compose -p kafka-infrastructure -f common.yml -f zookeeper.yml -f kafka_cluster.yml up -d --remove-orphans
