(ns scratch.core)

(def s #{1 2 3 4 5 6})

((fn [coll]
   (letfn [(f [curr x acc]
             (let [n (first x)
                   end (last x)]
               (letfn [(str-to-seq [s]
                         (map #(Integer/parseInt (str %)) (seq (str s)))
                         )
                       (power [start end acc]
                         (prn start)
                        (if (> start end)
                          acc
                          (recur (inc start) end (conj acc (str-to-seq start))))
                        )]
                 (prn (power (+ 122 curr) (+ 120 end) acc))
                 )
               )
             )]
     (if (empty? coll)
       #{}
       (set (f (first coll) (sort (rest coll)) []))))) s)

; (prn (power curr end acc))
; (power (+ 11 curr) (+ 10 end) acc)
; (power (+ 22 curr) (+ 20 end) acc)
; (power (+ 33 curr) (+ 30 end) acc)
; (power (+ 44 curr) (+ 40 end) acc)
; (power (+ 55 curr) (+ 50 end) acc)
; (power (+ 122 curr) (+ 120 end) acc)

;; {}
;; {1}
;; {2}
;; {3}
;; {4}
;; {5}
;; {6}
;; {1 2}
;; {1 3}
;; {1 4}
;; {1 5}
;; {1 6}
;; {2 3}
;; {2 4}
;; {2 5}
;; {2 6}
;; {3 4}
;; {3 5}
;; {3 6}
;; {4 5}
;; {4 6}
;; {5 6}
;; {1 2 3}
;; {1 2 4}
;; {1 2 5}
;; {1 2 6}
;; {1 3 4}
;; {1 3 5}
;; {1 3 6}
;; {1 4 5}
;; {1 4 6}
;; {1 5 6}
;; {2 3 4}
;; {2 3 5}
;; {2 4 6}
;; {2 3 6}
;; {2 4 5}
;; {2 5 6}
;; {3 4 5}
;; {3 4 6}
;; {3 5 6}
;; {4 5 6}
;; {1 2 3 4}
;; {1 2 3 5}
;; {1 2 3 6}
;; {1 2 4 5}
;; {1 2 4 6}
;; {1 2 5 6}
;; {1 3 4 5}
;; {1 3 4 6}
;; {1 3 5 6}
;; {1 4 5 6}
;; {2 3 4 5}
;; {2 3 4 6}
;; {2 3 5 6}
;; {2 4 5 6}
;; {3 4 5 6}
;; {1 2 3 4 5}
;; {1 2 3 4 6}
;; {1 2 3 5 6}
;; {1 2 4 5 6}
;; {1 3 4 5 6}
;; {2 3 4 5 6}
;; {1 2 3 4 5 6}
