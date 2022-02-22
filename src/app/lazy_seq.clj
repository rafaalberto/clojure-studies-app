(ns app.lazy-seq)

(def numbers '(1 2 3 4))

(def new-numbers (range 1 900001))

(defn add-items-recur [numbers new-numbers]
  (if-let [current-number (first new-numbers)]
    (recur (cons current-number numbers) (rest new-numbers))
    numbers))

(defn add-items-lazy [numbers new-numbers]
  (lazy-seq (if-let [current-number (first new-numbers)]
    (add-items-lazy (cons current-number numbers) (rest new-numbers))
    numbers)))

(println "Time with Recur: " (time (class (add-items-recur numbers new-numbers))))
(println "Time with Lazy-Seq: " (time (class (add-items-lazy numbers new-numbers))))