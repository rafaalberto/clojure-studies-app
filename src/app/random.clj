(ns app.random)

(defn random-transaction []
  {:value (* (rand-int 100001) 0.01M)
   :type  (rand-nth ["Deposit" "Withdraw"])})

;repeatedly - starts lazy sequence
;if number after repeatedly is not defined - it will be infinity
(def transactions (repeatedly 3 random-transaction))

(println (take 2 transactions))