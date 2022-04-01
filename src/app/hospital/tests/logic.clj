(ns app.hospital.tests.logic
  (:require [app.hospital.tests.model :as h.model]
            [schema.core :as s]))

(defn waiting-line-vacancy? [hospital department]
  (some-> hospital
          department
          count
          (< 5)))

(s/defn get-next :- h.model/PatientID
         [hospital :- h.model/Hospital
          department :- s/Keyword]
         (-> hospital
             department
             peek))

(s/defn attend :- h.model/Hospital
  [hospital :- h.model/Hospital
   department :- s/Keyword]
  (update hospital department pop))

(defn arrive [hospital department patient]
  (if (waiting-line-vacancy? hospital department)
    (update hospital department conj patient)
    (throw (ex-info "There is no vacancy to this department" {:patient patient}))))

(defn- same-quantity? [hospital hospital-updated from to]
  (= (+ (count (get hospital-updated from)) (count (get hospital-updated to)))
     (+ (count (get hospital from)) (count (get hospital to)))))

(s/defn transfer :- h.model/Hospital
  [hospital :- h.model/Hospital
   from :- s/Keyword
   to :- s/Keyword]
  {:pre  [(contains? hospital from) (contains? hospital to)]
   :post [(same-quantity? hospital % from to)]}
  (let [patient (get-next hospital from)]
    (-> hospital
        (attend from)
        (arrive to patient))))