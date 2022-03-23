(ns app.orders_logic
  (:require [app.orders-database :as a.database]))

(defn- total-of-item [[_ item]]
  (* (:quantity item 0) (:price item 0)))

(defn- total-of-order [order]
  (reduce + (map total-of-item order)))

(defn- total-amount [orders]
  (->> orders
       (map :items)
       (map total-of-order)
       (reduce +)))

(defn- totals-by-user [[user orders]]
  {:user-id      user
   :total-orders (count orders)
   :total-amount (total-amount orders)})

(defn report-by-user [order]
  (->> order
       (group-by :user)
       (map totals-by-user)))
