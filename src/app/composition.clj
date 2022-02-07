(ns app.composition)

(def transactions
  [{:type "Deposit" :amount 500.00 :currency "R$"}
   {:type "Withdraw" :amount 300.00 :currency "R$"}
   {:type "Withdraw" :amount 50.00 :currency "R$"}
   {:type "Deposit" :amount 1000.00 :currency "R$"}])

(defn transaction-symbol [transactions]
  (let [amount (:amount transactions)
        currency (:currency transactions)]
    (if (= (:type transactions) "Deposit")
      (str currency " +" amount)
      (str currency " -" amount))))

(println (transaction-symbol (first transactions)))