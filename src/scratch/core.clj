(ns scratch.core
  (require [clojure.string :as str :only (split-lines join)]))

(defn calc-limit [n]
  (let [log (Math/log n)
        loglog (Math/log log)
        logsum (+ log loglog)]
    (-> n (* logsum) int (+ 3))))

(defn primes [n]
  (let [root (-> n (Math/sqrt) inc int)
        sieve (boolean-array n true)]
    (loop [i 2]
      (when (< i (Math/sqrt n))
        (when (aget sieve i)
          (loop [j (* i 2)]
            (when (< j n)
              (aset sieve j false)
              (recur (+ j i)))))
        (recur (inc i))))
    (filter #(aget sieve %) (range 2 n))))

(defn nth-prime [n]
  (cond
    (= n 1) 2
    (= n 2) 3
    :else (last (take n (primes (calc-limit n))))))

(defn print-primes [[x & xs]]
  (prn x)
  (if (seq xs)
    (recur xs)))


(let [input "1\n10001"
      ranks (rest (map read-string (str/split-lines input)))
      primes (map nth-prime ranks)]
  (time (print-primes  primes)))  ; Elapsed time: 52.271 msecs
