#!/usr/bin/env bash

docker-compose -f common.yml -f zookeeper.yml -f kafka_cluster.yml up -d --remove-orphans
