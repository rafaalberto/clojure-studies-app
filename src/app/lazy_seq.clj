(ns app.lazy-seq)

(def numbers '(1 2 3 4))

(def new-numbers (range 1 10001))

(defn add-items [numbers new-numbers]
  (if-let [current-number (first new-numbers)]
    (recur (cons current-number numbers) (rest new-numbers))
    numbers))

(prn (add-items new-numbers numbers))