(ns app.vector)

(defn display-vector [numbers]
  (println "displaying country:" numbers))

(def european-countries ["UK" "Germany" "Portugal" "France" "Italy"])

(map display-vector european-countries)

(println "position 3: " (get european-countries 3))
(println "position last: " (last european-countries))
(println "first last: " (first european-countries))

;add new element to last position of vector
(println "add new country: " (conj european-countries "Spain"))

;immutable - the last item added is not here
(map display-vector european-countries)