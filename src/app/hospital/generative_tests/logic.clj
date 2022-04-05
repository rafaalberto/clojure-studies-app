(ns app.hospital.generative-tests.logic
  (:require [app.hospital.generative-tests.model :as h.model]
            [schema.core :as s]))

(defn waiting-line-vacancy? [hospital department]
  (some-> hospital
          department
          count
          (< 5)))

(s/defn get-next :- (s/maybe h.model/PatientID)
  [hospital :- h.model/Hospital
   department :- s/Keyword]
  (-> hospital
      department
      peek))

(defn attend [hospital department]
  (update hospital department pop))

(defn arrive [hospital department patient]
  (if (waiting-line-vacancy? hospital department)
    (update hospital department conj patient)
    (throw (IllegalStateException. "There is no vacancy to this department"))))

(s/defn transfer :- h.model/Hospital
  [hospital :- h.model/Hospital
   from :- s/Keyword
   to :- s/Keyword]
  (if-let [patient (get-next hospital from)]
    (-> hospital
        (attend from)
        (arrive to patient))
    hospital))

(defn total-of-patients [hospital]
  (reduce + (map count (vals hospital))))