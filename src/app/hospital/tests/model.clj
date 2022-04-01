(ns app.hospital.tests.model
  (:import (clojure.lang PersistentQueue))
  (:require [schema.core :as s]))

(def empty-queue PersistentQueue/EMPTY)

(s/def PatientID s/Str)
(s/def Department (s/->Queue PatientID))
(s/def Hospital {s/Keyword Department})