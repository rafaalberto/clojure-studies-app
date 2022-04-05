(ns app.generative-test
  (:require [clojure.test :refer :all]
            [app.hospital.generative-tests.logic :refer :all]))

(deftest waiting-line-vacancy?-test

  (testing "When waiting line is empty"
    (is (waiting-line-vacancy? {:waiting-line []} :waiting-line)))

  (testing "When there is vacancy"
    (is (waiting-line-vacancy? {:waiting-line [1 3 8 10]} :waiting-line)))

  (testing "When there is no vacancy"
    (is (not (waiting-line-vacancy? {:waiting-line [1 2 3 4 5]} :waiting-line)))))

(deftest arrive-to-hospital-test

  (testing "Arrive to waiting line"
    (is (= {:waiting-line [1 3 2]}
           (arrive {:waiting-line [1 3]} :waiting-line 2)))))