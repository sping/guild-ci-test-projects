//
//  GithubRepo.swift
//  GitViewer
//
//  Created by Sebastiaan Seegers on 14/05/2019.
//  Copyright © 2019 Sping. All rights reserved.
//

import UIKit

class GithubRepo: NSObject {
  var name: String?
  var htmlURL: String?

  convenience init(RepoName name: String, Url url: String) {
    self.init()
    self.name = name
    self.htmlURL = url
  }
}
