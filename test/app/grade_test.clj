(ns app.grade-test
  (:require [clojure.test :refer :all])
  (:require [app.grade :refer [result]]))

(deftest approved
  (testing "The final grade should be Approved"
    (is (= (result 9 8) "The final grade is: Approved"))))

(deftest exam
  (testing "The final grade should be Exam"
    (is (= (result 7 6) "The final grade is: Exam"))))

(deftest reproved
  (testing "The final grade should be Reproved"
    (is (= (result 4 5) "The final grade is: Reproved"))))