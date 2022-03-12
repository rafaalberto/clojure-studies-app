(ns app.some)

(println "even? " (some even? '(1 2 4 6)))
(println "even? " (some even? '(1 3 5 7)))

(def names ["Rafael" "Jim" "John" "Tom"])

(println "is there any Rafael? " (some #(= "Rafael" %) names))