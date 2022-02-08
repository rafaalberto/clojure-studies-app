(ns app.composition)

(def transactions
  [{:type "Deposit" :amount 523.31M :date "2022-01-01" :currency "R$"}
   {:type "Withdraw" :amount 300.00M :date "2022-01-06"}
   {:type "Withdraw" :amount 50.00M :date "2022-01-15" :currency "R$"}
   {:type "Deposit" :amount 1000.00M :date "2022-01-19" :currency "R$"}])

(defn amount-report [transactions]
  (let [currency (:currency transactions "$")
        amount (:amount transactions)]
    (if (= (:type transactions) "Deposit")
      (str currency " +" amount)
      (str currency " -" amount))))

(defn transactions-report [transactions]
  (str (:date transactions) " => "
       (amount-report transactions)))

(def currencies {:dollar {:price 5.44M :symbol "$"}})

(defn transactions-in-dollar [transactions]
  (let [dollar (:dollar currencies)]
    (assoc transactions
      :amount (* (:price dollar) (:amount transactions))
      :currency (:symbol dollar))))

(defn transactions-report-in-dollar [transactions]
  (-> (transactions-in-dollar transactions)
      (transactions-report)))

(println (map transactions-report-in-dollar transactions))