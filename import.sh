#!/bin/bash
mongorestore --db puntosDeAcceso --collection wifi /data/wifi.bson
mongoimport --db puntosDeAcceso --collection wifi --file /data/wifi.metadata.json