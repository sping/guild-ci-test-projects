//
//  UIViewControllerExtension.swift
//  GitViewer
//
//  Created by Sebastiaan Seegers on 14/05/2019.
//  Copyright © 2019 Sping. All rights reserved.
//

import UIKit
import Foundation
import PopupDialog
import SVProgressHUD

extension UIViewController {

  func showError(withMessage message: String) {
    DispatchQueue.main.async {
      let popup = PopupDialog(title: "Error", message: message)
      popup.buttonAlignment = .horizontal
      popup.transitionStyle = .zoomIn
      popup.addButton(DefaultButton(title: "OK", action: nil))
      self.hideLoader()
      self.present(popup, animated: true, completion: nil)
    }
  }

  func showLoader() {
    DispatchQueue.main.async {
      SVProgressHUD.show()
    }
  }

  func hideLoader() {
    DispatchQueue.main.async {
      SVProgressHUD.dismiss()
    }
  }
}
