//
//  UIColorExtension.swift
//  GitViewer
//
//  Created by Sebastiaan Seegers on 14/05/2019.
//  Copyright © 2019 Sping. All rights reserved.
//

import Foundation
import UIKit

extension UIColor {
  convenience init(hex: String) {
    var chars = Array(hex.hasPrefix("#") ? hex.dropFirst() : hex[...])
    let red, green, blue, alpha: CGFloat
    switch chars.count {
    case 3:
      chars = chars.flatMap { [$0, $0] }
      fallthrough
    case 6:
      chars = ["F", "F"] + chars
      fallthrough
    case 8:
      alpha = CGFloat(strtoul(String(chars[0...1]), nil, 16)) / 255
      red   = CGFloat(strtoul(String(chars[2...3]), nil, 16)) / 255
      green = CGFloat(strtoul(String(chars[4...5]), nil, 16)) / 255
      blue  = CGFloat(strtoul(String(chars[6...7]), nil, 16)) / 255
    default:
      alpha = 1
      red = 0
      green = 0
      blue = 0
    }
    self.init(red: red, green: green, blue: blue, alpha: alpha)
  }
}
