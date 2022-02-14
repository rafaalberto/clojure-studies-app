(ns app.mutability)

(def registers (atom ()))

(defn insert-transactions [transaction]
  (swap! registers conj transaction))

(insert-transactions {:value 10})
(insert-transactions {:value 50})
(insert-transactions {:value 150})

(println @registers)