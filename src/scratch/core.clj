(ns scratch.core
  (require [clojure.string :as str :only (split-lines join split)]))

(defn numberify [str]
  (vec (map read-string (str/split str #" "))))

(defrecord TreeNode [val left right])

(defn preprocess-input [n xs]
  (let [source (map vector (range 1 n) xs)]
    (->> source
         (map (fn [[k v]]
                {k v}))
         (into {}))))

(defn build-tree [val source]
  (when-let [[l r] (get source val)]
    (TreeNode. val (build-tree l source) (build-tree r source))))

(defn walk-tree [node curr swap-depth]
  (when node
    (let [left (:left node)
          right (:right node)
          val (:val node)]
      (if (= curr swap-depth)
        (TreeNode. val (walk-tree right (inc curr) swap-depth) (walk-tree left (inc curr) swap-depth))
        (TreeNode. val (walk-tree left (inc curr) swap-depth) (walk-tree right (inc curr) swap-depth))))))

(let [input "11\n2 3\n4 -1\n5 -1\n6 -1\n7 8\n-1 9\n-1 -1\n10 11\n-1 -1\n-1 -1\n-1 -1\n2\n2\n4"
      lines (str/split-lines input)
      tree-length (read-string (first lines))
      tree-lines (map numberify (drop 1 (take (inc tree-length) lines)))
      tree-source (preprocess-input tree-length tree-lines)
      tree (build-tree 1 tree-source)
      swap-depths (map read-string (vec (take-last (Integer/parseInt (get lines (inc tree-length))) lines)))]
  (walk-tree tree 1 2))
