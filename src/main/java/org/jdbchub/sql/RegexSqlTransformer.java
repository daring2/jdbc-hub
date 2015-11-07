package org.jdbchub.sql;

import com.typesafe.config.Config;
import java.util.List;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.toList;

public class RegexSqlTransformer implements SqlTransformer {

	final Config config;
	final List<Rule> rules;

	public RegexSqlTransformer(Config config) {
		this.config = config;
		this.rules = buildRules();
	}

	private List<Rule> buildRules() {
		Config rc = config.getConfig("rules");
		return rc.root().entrySet().stream()
			.map(ce -> new Rule(ce.getKey(), "" + ce.getValue().unwrapped()))
			.collect(toList());
	}

	@Override
	public String transform(String sql) {
		for (Rule rule : rules)
			sql = rule.apply(sql);
		return sql;
	}

	class Rule {
		final Pattern pattern;
		final String replacement;

		Rule(String regex, String replacement) {
			this.pattern = Pattern.compile(regex);
			this.replacement = replacement;
		}

		String apply(String sql) {
			return pattern.matcher(sql).replaceAll(replacement);
		}

	}

}
