(ns elite-rpg.scene
  (:require
    [elite-rpg.sprites :as sprites]))

(def a:ship-x (atom 370))

(defn build-scene [app]
  (sprites/add-sprite app :ship1 :ship @a:ship-x 500 64 64))

(def a:direction (atom :none))

(defn on-input [code down?]
  (if down?
    (case code
      :ArrowLeft (reset! a:direction :left)
      :ArrowRight (reset! a:direction :right)
      ;else
      nil)
    ;else
    (case code
      :ArrowLeft (reset! a:direction :none)
      :ArrowRight (reset! a:direction :none)
      ;else
      nil)))


(defn on-tick [delta]
  (when-let [ship (sprites/get-sprite :ship1)]
    (case @a:direction
      :right (swap! a:ship-x inc)
      :left (swap! a:ship-x dec)
      :none nil)
    (set! (.-position.x ship) @a:ship-x)))
