(ns app.hospital.generative-tests.logic)

(defn waiting-line-vacancy? [hospital department]
  (some-> hospital
        department
        count
        (< 5)))

(defn arrive [hospital department patient]
  (if (waiting-line-vacancy? hospital department)
    (update hospital department conj patient)
    (throw (IllegalStateException. "There is no vacancy to this department"))))

