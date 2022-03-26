(ns app.hospital.atom.logic)

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

(defn- next-person
  "Returns next person from line"
  [hospital department]
  (-> hospital
      department
      peek))

(defn transfer [hospital from to]
  (let [person (next-person hospital from)]
    (-> hospital
        (attend from)
        (arrive-at to person))))