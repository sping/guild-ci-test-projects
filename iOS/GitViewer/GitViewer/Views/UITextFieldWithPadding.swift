//
//  UITextFieldWithPadding.swift
//  GitViewer
//
//  Created by Sebastiaan Seegers on 14/05/2019.
//  Copyright © 2019 Sping. All rights reserved.
//

import Foundation
import UIKit

class UITextFieldWithPadding: UITextField {

  let padding = UIEdgeInsets(top: 0, left: 10, bottom: 0, right: 10)

  required init?(coder aDecoder: NSCoder) {
    super.init(coder: aDecoder)
  }

  override func textRect(forBounds bounds: CGRect) -> CGRect {
    return bounds.inset(by: padding)
  }
  override func placeholderRect(forBounds bounds: CGRect) -> CGRect {
    return bounds.inset(by: padding)
  }

  override func editingRect(forBounds bounds: CGRect) -> CGRect {
    return bounds.inset(by: padding)
  }
}
