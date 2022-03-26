(ns app.hospital.logic)

(defn- line-available? [hospital department]
  (-> hospital
      (get department)
      count
      (< 5)))

(defn arrive-at [hospital department person]
  (println "Attempt to add person:" person)
  (Thread/sleep (* (rand) 1000))
  (if (line-available? hospital department)
    (update hospital department conj person)
    (throw (ex-info "There is no available capacity" {:trying-add person}))))

(defn attend [hospital department]
  (update hospital department pop))