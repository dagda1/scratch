(ns scratch.core
  (require [clojure.string :as str :only (split-lines join split)]))

(defn numberify [str]
  (vec (map read-string (str/split str #" "))))

(defrecord TreeNode [val left right])

(defn tree-map [idx itm]
  (let [side (if (= 0 idx) :left :right)]
    {:side side :index idx :val itm}))

(defn append-to-tree [node x]
  (reduce (fn [t b]
            (let [index (if (= b :left) 0 1)
                  val (nth x index)]
              (if-not (= val -1)
                (assoc t b (TreeNode. val nil nil))
                t))
            ) node [:left :right]))

(defn build-tree [node xs]
  (let [counter (atom 1)]
    (reduce (fn [t x]
              (reduce (fn [n l]
                        (if-not (= (:val l) -1)
                          (let [next-branch (nth xs @counter)
                                ]
                            (swap! counter inc)
                            (prn "===============")
                            (prn l)
                            (prn next-branch)
                            (prn "===============")
                            )
                          n
                          )
                       )t (map-indexed tree-map x))) node xs)))

; process [2, 3]
; pop 2 on stack
; 2 gets  [4, -1]
; pop on stack
; 3 gets  [5, -1]
; pop 4 on stack
; 4 gets  [6, -1]
; pop 5 on stack
; 5 gets [7, 8]
; pop 6 on stack
; 6 gets [-1, 9]
; pop 7 on stack
; 7 gets [-1, -1]
; pop 8 on stack
; 8 gets [10, 11]
; pop 9 on stack
; 9 gets [-1 -1]
; pop 10 on stack
; 10 gets [-1 -1]
; pop 11 on stack
; 11 gets [-1 -1]
; (assoc node :left (make-tree left))
;
;                  1
;                 / \
;                2   3
;               /    /
;              4    5
;             /    /
;            /    /\
;           6    7  8
;            \     / \
;             9   10 11
(let [input "11\n2 3\n4 -1\n5 -1\n6 -1\n7 8\n-1 9\n-1 -1\n10 11\n-1 -1\n-1 -1\n-1 -1"
      lines (str/split-lines input)
      tl (read-string (first lines))
      tree-lines (map numberify (drop 1 (take (inc tl) lines)))
      tree (build-tree (TreeNode. 1 nil nil) tree-lines)])
