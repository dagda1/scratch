(ns scratch.core
  (require [clojure.string :as str :only (split-lines join)]))

(defn is-prime? [n primes]
  (prn (str "n = " n))
  (if (= n 2)
    true
    (let [root (Math/floor (int (Math/sqrt n)))]
      (loop [[x & xs] primes]
        (prn x)
        (if (= 0 (mod n x))
            false
            (if (seq xs)
              (recur xs)
              true))))))

(def mem-is-prime? (memoize is-prime?))

(defn n-primes [n]
  (loop [curr 3 acc [2]]
    (if (= (count acc) n)
      acc
      (recur (+ 2 curr) (if (mem-is-prime? curr acc)
                          (conj acc curr)
                          acc)))))

(defn nth-prime [n]
  (last (n-primes n)))

(defn print-primes [[x & xs]]
  (prn x)
  (if (seq xs)
    (recur xs)))

(let [input "1\n101"
      ranks (rest (map read-string (str/split-lines input)))
      primes (map nth-prime ranks)]
  (time (print-primes  primes)))
