//
//  ViewController.swift
//  GitViewer
//
//  Created by Sebastiaan Seegers on 14/05/2019.
//  Copyright © 2019 Sping. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {

  @IBOutlet weak var usernameTextField: UITextField!
  @IBOutlet weak var passwordTextField: UITextField!
  @IBOutlet weak var loginButton: UIButton!

  override func viewDidLoad() {
    super.viewDidLoad()
    // Do any additional setup after loading the view.
  }

  override func viewWillAppear(_ animated: Bool) {
    super.viewWillAppear(animated)
    showLoader()
  }

  override func viewDidAppear(_ animated: Bool) {
    super.viewDidAppear(animated)
    self.hideLoader()
    if !AppUtils.shared.userToken().isEmpty {
      self.performSegue(withIdentifier: "showListViewController", sender: self)
    }
  }

  @IBAction func login(_ sender: Any) {
    if !(usernameTextField.text ?? "").isEmpty && !(passwordTextField.text ?? "").isEmpty {
      handleLogin()
    } else {
      showError(withMessage: "Username or password not filled in")
    }
  }

  private func handleLogin() {
    if !AppUtils.shared.hasInternet() {
      showError(withMessage: "Error not connected to the internet")
      return
    }

    showLoader()
    AuthenticationService.authenticate(Username: usernameTextField.text!, Password: passwordTextField.text!) { success in
      self.hideLoader()
      if success {
        self.performSegue(withIdentifier: "showListViewController", sender: self)
      } else {
        self.showError(withMessage: "Error connecting to GitHub please try again later!")
      }
    }
  }
}
