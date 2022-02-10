(ns app.composition-transactions)

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

(def currencies
  {:dollar {:price 5.44M :symbol "$"}
   :euro   {:price 6.00M :symbol "€"}})

(defn transactions-in-dollar [transactions]
  (let [{{price :price symbol :symbol} :dollar} currencies]
    (assoc transactions
      :amount (* price (:amount transactions))
      :currency symbol)))

(defn transactions-by-currency [currency transactions]
  (let [{{price :price symbol :symbol} currency} currencies]
    (assoc transactions
      :amount (* price (:amount transactions))
      :currency symbol)))

(def report-in-dollar (comp transactions-report transactions-in-dollar))

(println (map report-in-dollar transactions))

(println (transactions-by-currency :euro (first transactions)))

(def transactions-euro (partial transactions-by-currency :euro))

(println (transactions-euro (first transactions)))