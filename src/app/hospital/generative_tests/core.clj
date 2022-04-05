(ns app.hospital.generative_tests.core
  (:use [clojure pprint])
  (:require [clojure.test.check.generators :as gen]
            [schema-generators.generators :as sg]
            [app.hospital.generative-tests.model :as h.model]))

(println "boolean gen:" (gen/sample gen/boolean 5))
(println "small int gen:" (gen/sample gen/small-integer 4))
(println "vector with small int gen:" (gen/sample (gen/vector gen/small-integer 2) 5))
(println "string gen:" (gen/sample gen/string-alphanumeric 4))

(println "PatientID:" (sg/sample 5 h.model/PatientID))
(pprint (sg/sample 3 h.model/Department))
(pprint (sg/sample 3 h.model/Hospital))
(pprint (sg/generate h.model/Hospital))