//
//  ReposListViewController.swift
//  GitViewer
//
//  Created by Sebastiaan Seegers on 14/05/2019.
//  Copyright © 2019 Sping. All rights reserved.
//

import UIKit
import SAMKeychain

class ReposListViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {

  @IBOutlet weak var tableView: UITableView!
  private var repos: [GithubRepo] = []

  override func viewDidLoad() {
    super.viewDidLoad()
    self.showLoader()
    self.tableView.layer.cornerRadius = 10
    RepoService.getRepos { repos, error in
      if error || repos == nil {
        self.showError(withMessage: "Error getting repo's please pull to refresh to try again")
        return
      }

      self.hideLoader()
      self.repos = repos!
      self.tableView.reloadData()
    }
  }

  func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
    return repos.count
  }

  func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
    let cell: UITableViewCell = tableView.dequeueReusableCell(withIdentifier: "RepoCell", for: indexPath)
    let repo: GithubRepo = repos[indexPath.row]
    cell.textLabel?.text = repo.name ?? ""
    cell.detailTextLabel?.text = repo.htmlURL ?? ""
    return cell
  }

  @IBAction func logout(_ sender: Any) {
    SAMKeychain.deletePassword(forService: SAMKEYCHAINSERVICENAME, account: SAMKEYCHAINACCOUNT)
    self.dismiss(animated: true, completion: nil)
  }
}
