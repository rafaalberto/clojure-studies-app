(ns app.hospital.tests.logic)

(defn waiting-line-vacancy? [hospital department]
  (some-> hospital
          department
          count
          (< 5)))

(defn arrive [hospital department patient]
  (if (waiting-line-vacancy? hospital department)
    (update hospital department conj patient)
    (throw (ex-info "There is no vacancy to this department" {:patient patient}))))

(defn get-next [hospital department]
  (-> hospital
      department
      peek))

(defn attend [hospital department]
  (update hospital department pop))

(defn transfer
  [hospital from to]
  ;{:pre [(contains? hospital from)]}
  (let [patient (get-next hospital from)]
    (-> hospital
        (attend from)
        (arrive to patient))))